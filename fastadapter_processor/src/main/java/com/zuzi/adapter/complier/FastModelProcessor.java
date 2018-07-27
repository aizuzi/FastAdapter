package com.zuzi.adapter.complier;

import com.google.auto.service.AutoService;
import com.zuzi.adapter.annotation.RecyclerItemLayoutId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author liyi
 * create at 2018/5/30
 **/
@AutoService(Processor.class)
public class FastModelProcessor extends AbstractProcessor {
  //region Fields
  private Messager mMessager;
  private Filer mFiler;
  private Elements mElements;

  @Override
  public synchronized void init(final ProcessingEnvironment pProcessingEnvironment) {
    super.init(pProcessingEnvironment);
    mMessager = pProcessingEnvironment.getMessager();
    mFiler = pProcessingEnvironment.getFiler();
    mElements = pProcessingEnvironment.getElementUtils();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    final Set<String> lAnnotations = new HashSet<>();
    lAnnotations.add(RecyclerItemLayoutId.class.getCanonicalName());
    //lAnnotations.add(FastAttribute.class.getCanonicalName());
    return lAnnotations;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(final Set<? extends TypeElement> pSet,
      final RoundEnvironment pRoundEnvironment) {
    final ArrayList<AnnotatedClass> lAnnotatedClasses;
    try {
      lAnnotatedClasses = new AnnotatedMethods(pRoundEnvironment).enclosingClasses();
      for (final AnnotatedClass lClass : lAnnotatedClasses) {
        PackageElement packageElement = mElements.getPackageOf(lClass.enclosingClass());
        String packageName = packageElement.getQualifiedName().toString();

        lClass.writeInto(mFiler, mMessager, packageName);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return false;
  }
}
