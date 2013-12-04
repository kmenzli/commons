package org.exoplatform.commons.metadata;

import org.exoplatform.commons.versioning.ProductVersion;
import org.exoplatform.commons.versioning.VersionRange;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: menzli
 * Date: 04/12/13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public interface Product extends Comparable<Product>  {
    String RELEASE_VERSION = "RELEASE";

    String LATEST_VERSION = "LATEST";

    String SNAPSHOT_VERSION = "SNAPSHOT";

    Pattern VERSION_FILE_PATTERN = Pattern.compile( "^(.*)-([0-9]{8}.[0-9]{6})-([0-9]+)$" );

    String getGroupId();

    String getArtifactId();

    String getVersion();

    void setVersion( String version );

    String getType();

    String getBaseVersion();

    void setBaseVersion( String baseVersion );

    String getId();

    VersionRange getVersionRange();

    void setVersionRange( VersionRange newRange );

    boolean isSnapshot();

    boolean isRelease();

    void setRelease( boolean release );

    List<ProductVersion> getAvailableVersions();

    void setAvailableVersions( List<ProductVersion> versions );


}
