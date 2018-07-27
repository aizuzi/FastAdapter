package com.zuzi.adapter.complier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.zuzi.adapter.annotation.FastAttribute;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * 创建 get 和 set方法
 *
 * @author liyi
 * create at 2018/5/29
 **/
public final class GeneratedFieldAndMethod {
  //region Fields
  private final TypeElement mClassElement;
  private final AnnotatedField mAnnotatedField;
  //endregion

  //region Constructor
  public GeneratedFieldAndMethod(final TypeElement pMethodElement,
      final AnnotatedField pClassElement) {
    mClassElement = pMethodElement;
    mAnnotatedField = pClassElement;
  }
  //endregion

  //region Visible API
  public FieldSpec buildField() {

    FieldSpec.Builder fieldSpec =
        FieldSpec.builder(TypeName.get(mAnnotatedField.type), mAnnotatedField.name);

    if (mAnnotatedField.annotationMirrors != null) {
      for (AnnotationMirror annotationMirror :
          mAnnotatedField.annotationMirrors) {
        TypeElement annotationElement =
            (TypeElement) annotationMirror.getAnnotationType().asElement();
        if (annotationElement.getQualifiedName().contentEquals(Override.class.getName())) {
          // Don't copy @Override if present, since we will be adding our own @Override in the
          // implementation.
          continue;
        }

        fieldSpec.addAnnotation(AnnotationSpec.get(annotationMirror));
      }
    }

    return fieldSpec.build();
  }

  public MethodSpec buildGetMethod() {

    String methoName =
        upperCase(mAnnotatedField.name);
    final MethodSpec.Builder lBuilder =
        MethodSpec.methodBuilder("get" + methoName)
            .addModifiers(Modifier.PUBLIC);

    lBuilder.returns(TypeName.get(mAnnotatedField.type));

    lBuilder.addCode("return " + mAnnotatedField.name + ";\n");

    return lBuilder.build();
  }

  public MethodSpec buildSetMethod() {

    String methoName =
        upperCase(mAnnotatedField.name);
    final MethodSpec.Builder lBuilder =
        MethodSpec.methodBuilder("set" + methoName)
            .addModifiers(Modifier.PUBLIC);

    lBuilder.addParameter(TypeName.get(mAnnotatedField.type), mAnnotatedField.name);
    lBuilder.addCode("this." + mAnnotatedField.name + " = " + mAnnotatedField.name + " ;\n");

    return lBuilder.build();
  }

  public MethodSpec buildBuilderSetMethod(TypeName resultName) {

    String methoName =
        upperCase(mAnnotatedField.name);
    final MethodSpec.Builder lBuilder =
        MethodSpec.methodBuilder("set" + methoName)
            .addModifiers(Modifier.PUBLIC);

    lBuilder.addParameter(TypeName.get(mAnnotatedField.type), mAnnotatedField.name);
    lBuilder.addCode("super." + mAnnotatedField.name + " = " + mAnnotatedField.name + " ;\n");

    FastAttribute fastAttribute = mAnnotatedField.getFastAttribute();
    if(fastAttribute.bindViewId() != 0){
      lBuilder.addCode("putValue(" + fastAttribute.bindViewId() + ", " + mAnnotatedField.name+");");
    }
    lBuilder.addCode("return this;\n");
    lBuilder.returns(resultName);

    return lBuilder.build();
  }


  public String upperCase(String str) {
    if (str.length() == 1) {
      return str.substring(0, 1).toUpperCase();
    }
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }
}
