package com.zuzi.adapter.complier;

import com.zuzi.adapter.Utils;
import com.zuzi.adapter.annotation.FastAttribute;
import com.zuzi.adapter.annotation.RecyclerItemLayoutId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * @author liyi
 * create at 2018/5/30
 **/
public final class AnnotatedMethods {
  //region Fields
  private final RoundEnvironment mRoundEnvironment;
  //endregion

  //region Constructor
  public AnnotatedMethods(final RoundEnvironment pRoundEnvironment) {
    mRoundEnvironment = pRoundEnvironment;
  }
  //endregion

  //region Visible API
  public ArrayList<AnnotatedClass> enclosingClasses() throws ClassNotFoundException {

    ArrayList<AnnotatedClass> lAnnotatedClasses = new ArrayList<>();

    //Builder 模式
    final Set<? extends Element> buildElements =
        mRoundEnvironment.getElementsAnnotatedWith(RecyclerItemLayoutId.class);

    for (final Element lElement : buildElements) {
      if (!Utils.isSubtypeOfType(lElement.asType(), Utils.TYPE_BASEHOLDER)) {
        throw new IllegalStateException(lElement.asType()
            + " The class of RecyclerItemLayoutId must extends FastBaseHolder");
      }

      List<? extends Element> elementList = lElement.getEnclosedElements();
      final AnnotatedClass lAnnotatedClass = new AnnotatedClass((TypeElement) lElement);

      for (Element item :
          elementList) {
        FastAttribute fastAttribute = item.getAnnotation(FastAttribute.class);
        if (item.getKind().isField() && fastAttribute != null) {

          //私有属性不支持
          if (item.getModifiers().contains(Modifier.PRIVATE)) {
            throw new IllegalStateException(
                item + " modifiers error, Private property is not supported");
          }

          TypeMirror resultType = item.asType();
          Name fieldName = item.getSimpleName();

          AnnotatedField annotatedField = new AnnotatedField();
          annotatedField.setName(fieldName.toString());
          annotatedField.setType(resultType);
          annotatedField.setmIsStaticField(item.getModifiers().contains(
              Modifier.STATIC));
          annotatedField.setAnnotationMirrors(item.getAnnotationMirrors());
          annotatedField.setFastAttribute(fastAttribute);

          lAnnotatedClass.addField(annotatedField);
        }
      }
      lAnnotatedClasses.add(lAnnotatedClass);
    }

    return lAnnotatedClasses;
  }
  //endregion
}
