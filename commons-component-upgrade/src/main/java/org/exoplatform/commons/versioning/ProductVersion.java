package org.exoplatform.commons.versioning;

/**
 * Created with IntelliJ IDEA.
 * User: menzli
 * Date: 04/12/13
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public interface ProductVersion extends Comparable<ProductVersion> {

    int getMajorVersion();

    int getMinorVersion();

    int getIncrementalVersion();

    int getBuildNumber();

    String getQualifier();

    void parseVersion( String version );

}
