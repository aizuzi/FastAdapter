package com.zuzi.adapter.complier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * @author liyi
 * create at 2018/5/30
 **/
public final class AnnotatedClass {
  private final TypeElement mEnclosingClass;
  private final List<AnnotatedField> mFields;
  private final List<ExecutableElement> mMethods;

  public AnnotatedClass(final TypeElement pEnclosingClass) {
    this(pEnclosingClass, new ArrayList<ExecutableElement>());
  }

  public AnnotatedClass(final TypeElement pEnclosingClass, final List<ExecutableElement> pMethods) {
    mEnclosingClass = pEnclosingClass;
    mMethods = pMethods;
    mFields = new ArrayList<>();
  }

  public TypeElement enclosingClass() {
    return mEnclosingClass;
  }

  public void addField(final AnnotatedField pMethod) {
    mFields.add(pMethod);
  }

  public void addMethod(final ExecutableElement pMethod) {
    mMethods.add(pMethod);
  }

  public void writeInto(final Filer pFiler, final Messager pMessager, final String lPackageName) {
    //final GeneratedClass lGeneratedClass =
    //    new GeneratedClass(mEnclosingClass, lPackageName, mMethods, mFields);

    TypeSpec lTypeSpecGeneratedClass =
        new GeneratedBuilderClass(mEnclosingClass, lPackageName, mFields).buildFieldTypeSpec();
    // create generated class to a file
    try {
      JavaFile.builder(lPackageName, lTypeSpecGeneratedClass)
          .build()
          .writeTo(pFiler);
    } catch (IOException pE) {
      logError(pMessager, mEnclosingClass, "error while writing generated class "+pE);
    }
  }

  private void logError(final Messager pMessager, final Element pElement, final String pMessage,
      final Object... pArgs) {
    pMessager.printMessage(Diagnostic.Kind.ERROR, String.format(pMessage, pArgs), pElement);
  }
}
