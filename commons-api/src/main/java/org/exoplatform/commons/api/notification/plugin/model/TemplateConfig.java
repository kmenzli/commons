/*
 * Copyright (C) 2003-2013 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU Affero General Public License
* as published by the Free Software Foundation; either version 3
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.commons.api.notification.plugin.model;

import java.util.HashMap;
import java.util.Map;

public class TemplateConfig {
  public static final String  DEFAULT_SRC_RESOURCE_BUNDLE_KEY = "locale.notification.template.NotificationTemplate";
  
  public static final String  DEFAULT_SRC_RESOURCE_TEMPLATE_KEY = "war:/notification/templates";

  public static final String  SUBJECT_KEY              = "subject";

  public static final String  DIGEST_KEY               = "digest";

  public static final String  DIGEST_ONE_KEY           = "digest.one";

  public static final String  DIGEST_THREE_KEY         = "digest.three";

  public static final String  DIGEST_MORE_KEY          = "digest.more";

  public static final String  FOOTER_KEY               = "footer";

  private String              providerId;

  private String              localeResouceBundle;

  private String              localeResouceTemplate;

  private Map<String, String> keyMapping               = new HashMap<String, String>();

  public TemplateConfig() {
    localeResouceBundle = DEFAULT_SRC_RESOURCE_BUNDLE_KEY;
  }

  public TemplateConfig(String providerId) {
    this();
    this.providerId = providerId;
  }

  /**
   * @return the providerId
   */
  public String getProviderId() {
    return providerId;
  }

  /**
   * @param providerId the providerId to set
   */
  public TemplateConfig setProviderId(String providerId) {
    this.providerId = providerId;
    return this;
  }

  /**
   * @return the localeResouceBundle
   */
  public String getLocaleResouceBundle() {
    return localeResouceBundle;
  }

  /**
   * @param localeResouceBundle the localeResouceBundle to set
   */
  public void setLocaleResouceBundle(String localeResouceBundle) {
    this.localeResouceBundle = localeResouceBundle;
  }

  /**
   * @return the localeResouceTemplate
   */
  public String getLocaleResouceTemplate() {
    if(localeResouceTemplate == null) {
      localeResouceTemplate = new StringBuffer(DEFAULT_SRC_RESOURCE_TEMPLATE_KEY)
                                .append("/").append(providerId).append(".gtmpl").toString();
    }
    return localeResouceTemplate;
  }

  /**
   * @param localeResouceTemplate the localeResouceTemplate to set
   */
  public void setLocaleResouceTemplate(String localeResouceTemplate) {
    this.localeResouceTemplate = localeResouceTemplate;
  }

  /**
   * @return the keyMapping
   */
  public Map<String, String> getKeyMapping() {
    return keyMapping;
  }

  /**
   * @param keyMapping the keyMapping to set
   */
  public void setKeyMapping(Map<String, String> keyMapping) {
    this.keyMapping = keyMapping;
  }

  public TemplateConfig addKeyMapping(String key, String value) {
    this.keyMapping.put(key, value);
    return this;
  }

  public String getKeyValue(String key, String defaultValue) {
    if (keyMapping.containsKey(key)) {
      return keyMapping.get(key);
    }
    return defaultValue;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TemplateConfig) {
      return ((TemplateConfig) obj).getProviderId().equals(this.getProviderId());
    }
    return false;
  }

}