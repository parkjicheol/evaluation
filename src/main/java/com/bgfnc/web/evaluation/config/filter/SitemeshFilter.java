package com.bgfnc.web.evaluation.config.filter;

import com.bgfnc.web.evaluation.config.SpringDecoratorConfiguration;
import org.sitemesh.DecoratorSelector;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.config.PathBasedDecoratorSelector;
import org.sitemesh.config.PathMapper;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        DecoratorSelector selector = new PathBasedDecoratorSelector()
            .put("/*", "/resources/templates/sitemesh/decorator.html");

        PathMapper pathMapper = new PathMapper();
        pathMapper.put("/logout", true);
        pathMapper.put("/error", true);
        pathMapper.put("/static", true);
        pathMapper.put("/ajax", true);
        pathMapper.put("/upload", true);

        builder.setCustomDecoratorSelector(new SpringDecoratorConfiguration(selector, pathMapper));
    }
}
