package org.yesworkflow.extract;

import java.util.List;
import java.util.Map;

import org.yesworkflow.Language;
import org.yesworkflow.YWStage;
import org.yesworkflow.annotations.Annotation;
import org.yesworkflow.exceptions.YWToolUsageException;

public interface Extractor extends YWStage {
    Extractor configure(String key, Object value) throws Exception;
    Extractor configure(Map<String, Object> config) throws Exception;
    Extractor extract() throws Exception;
    Language getLanguage();
    List<String> getLines();
    List<String> getComments();
    List<Annotation> getAnnotations();
    DefaultExtractor setLanguageBySource(String path) throws YWToolUsageException;
}

