package com.zuzi.adapter.complier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.zuzi.adapter.annotation.FastAttribute;
import com.zuzi.adapter.annotation.RecyclerItemLayoutId;
import java.util.List;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * @author liyi
 * create at 2018/5/30
 **/
public final class GeneratedBuilderClass {
  //region Fields
  private final TypeElement mClassElement;
  private final String packageName;
  private final List<AnnotatedField> mFields;
  //endregion

  //region Constructor
  public GeneratedBuilderClass(final TypeElement pClassElement, final String packageName,
      List<AnnotatedField> mFields) {
    mClassElement = pClassElement;
    this.packageName = packageName;
    this.mFields = mFields;
  }
  //endregion

  //region Visible API
  public TypeSpec buildFieldTypeSpec() {
    final String lClassName = getClassName();
    final TypeSpec.Builder lClassBuilder = TypeSpec.classBuilder(lClassName)
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

    //创建Field
    //创建 get 和 set方法
    for (final AnnotatedField annotatedField : mFields) {
      GeneratedFieldAndMethod generatedFieldAndMethod =
          new GeneratedFieldAndMethod(mClassElement, annotatedField);

      lClassBuilder.addMethod(
          generatedFieldAndMethod.buildBuilderSetMethod(
              ClassName.get(packageName, lClassName)));
    }

    if (mFields.size() > 0) {
      lClassBuilder.addMethod(buildToStringMethod());
    }
    lClassBuilder.addMethod(buildItemTypeMethod());
    lClassBuilder.superclass(TypeName.get(mClassElement.asType()));

    return lClassBuilder.build();
  }

  public String getClassName() {

    final String lQualifiedName = mClassElement.getQualifiedName().toString();

    String name = lQualifiedName.replace(packageName, "");

    if (name.length() > 0) {
      //内部类转换为_
      name = name.substring(1).replace(".", "_");
    }

    return name + "_";
  }
  //endregion

  private MethodSpec buildToStringMethod() {

    final MethodSpec.Builder lBuilder =
        MethodSpec.methodBuilder("toString")
            .returns(String.class)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addAnnotation(Override.class);

    lBuilder.addCode("return ");

    for (int i = 0, size = mFields.size(); i < size; i++) {
      AnnotatedField annotatedField = mFields.get(i);
      lBuilder.addCode("\"" + annotatedField.name + " = \"" + "  +" + annotatedField.name);
      if (i != size - 1) {
        lBuilder.addCode(" + ");
      }
    }
    lBuilder.addCode(";\n");

    return lBuilder.build();
  }

  private MethodSpec buildItemTypeMethod() {

    final MethodSpec.Builder lBuilder =
        MethodSpec.methodBuilder("getItemType")
            .returns(TypeName.INT)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addAnnotation(Override.class);

    RecyclerItemLayoutId recyclerItemLayoutId =
        mClassElement.getAnnotation(RecyclerItemLayoutId.class);

    lBuilder.addCode("return ");
    if (recyclerItemLayoutId != null) {
      lBuilder.addCode("" + recyclerItemLayoutId.value());
    }

    lBuilder.addCode(";\n");

    return lBuilder.build();
  }

  private MethodSpec buildSetValuesMethod() {

    final MethodSpec.Builder lBuilder =
        MethodSpec.methodBuilder("setValues")
            .returns(TypeName.VOID)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

    for (int i = 0, size = mFields.size(); i < size; i++) {
      AnnotatedField annotatedField = mFields.get(i);
      FastAttribute fastAttribute = annotatedField.getFastAttribute();

      lBuilder.addCode("putValue(" + fastAttribute.bindViewId() + ", " + annotatedField.name);
    }

    return lBuilder.build();
  }
}
