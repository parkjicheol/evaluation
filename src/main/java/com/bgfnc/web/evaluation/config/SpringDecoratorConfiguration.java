package com.bgfnc.web.evaluation.config;

import org.sitemesh.DecoratorSelector;
import org.sitemesh.SiteMeshContext;
import org.sitemesh.config.PathMapper;
import org.sitemesh.content.Content;
import org.sitemesh.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.site.SitePreference;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("rawtypes")
public class SpringDecoratorConfiguration implements DecoratorSelector {

    private final DecoratorSelector decoratorSelector;
    private final PathMapper pathMapper;
    private final String[] EMPTY = {};

    public SpringDecoratorConfiguration(DecoratorSelector decoratorSelector, PathMapper pathMapper) {
        this.decoratorSelector = decoratorSelector;
        this.pathMapper = pathMapper;
    }

    public String[] selectDecoratorPaths(Content content, SiteMeshContext siteMeshContext) throws IOException {
        SitePreference sitePreference = (SitePreference) ((WebAppContext)siteMeshContext).getRequest().getAttribute("currentSitePreference");
        if (sitePreference == null) {
            return EMPTY;
        }

        final String[] decorators = decoratorSelector.selectDecoratorPaths(content, siteMeshContext);
        Object exclude = pathMapper.get(siteMeshContext.getPath());
        boolean result = exclude == null;

        if (result) {
            return EMPTY;
        }

        if (sitePreference.isMobile()) {
            if (decorators != null && decorators.length > 0) {
                String[] tempDecorators = new String[decorators.length];
                Stream<String> stream = Arrays.stream(decorators);
                IntStream.range(0, decorators.length)
                        .forEach(index -> {
                            StringBuilder stringBuilder = new StringBuilder(tempDecorators[index]);
                            tempDecorators[index] = stringBuilder.insert(18, sitePreference.name().toLowerCase() + "/").toString();
                        });
                return tempDecorators;
            }
        }

        return decorators;
    }

}
