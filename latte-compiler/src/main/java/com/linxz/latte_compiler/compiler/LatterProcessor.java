package com.linxz.latte_compiler.compiler;

import com.google.auto.service.AutoService;
import com.linxz.latte_annotations.annotions.AppRegisterGenerator;
import com.linxz.latte_annotations.annotions.EntryGenerator;
import com.linxz.latte_annotations.annotions.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * <p>
 * <p>
 * ver     date      		author
 * V1.0   2018/02/26 23:59  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
@AutoService(Processor.class)
public class LatterProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations() {
        final Set<Class<? extends Annotation>> annotions = new LinkedHashSet<>();
        annotions.add(EntryGenerator.class);
        annotions.add(PayEntryGenerator.class);
        annotions.add(AppRegisterGenerator.class);
        return annotions;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterEntryCode(roundEnvironment);
        return true;
    }

    private void generateEntryCode(RoundEnvironment env){
         final EntryVisitor entryVisitor=new EntryVisitor();
         entryVisitor.setmFiler(processingEnv.getFiler());
         scan(env,EntryGenerator.class,entryVisitor);
    }

    private void generatePayEntryCode(RoundEnvironment env){
        final PayVisitor payVisitor=new PayVisitor();
        payVisitor.setmFiler(processingEnv.getFiler());
        scan(env,PayEntryGenerator.class,payVisitor);
    }

    private void generateAppRegisterEntryCode(RoundEnvironment env){
        final AppRegisterVisitor appRegisterVisitor=new AppRegisterVisitor();
        appRegisterVisitor.setmFiler(processingEnv.getFiler());
        scan(env,AppRegisterGenerator.class,appRegisterVisitor);
    }

    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

}
