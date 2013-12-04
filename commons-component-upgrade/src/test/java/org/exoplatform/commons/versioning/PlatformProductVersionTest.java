package org.exoplatform.commons.versioning;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: menzli
 * Date: 04/12/13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public class PlatformProductVersionTest extends TestCase {
    private ProductVersion newArtifactVersion( String version )
    {
        return new PlatformProductVersion( version );
    }
    private void checkVersionParsing( String version, int major, int minor, int incremental, int buildnumber, String qualifier ) {

        ProductVersion productVersion = newArtifactVersion( version );
        String parsed = "'" + version + "' parsed as ('" + productVersion.getMajorVersion() + "', '"
                + productVersion.getMinorVersion() + "', '" + productVersion.getIncrementalVersion() + "', '"
                + productVersion.getBuildNumber() + "', '" + productVersion.getQualifier() + "'), ";
        TestCase.assertEquals(parsed + "check major version", major, productVersion.getMajorVersion());
        TestCase.assertEquals(parsed + "check minor version", minor, productVersion.getMinorVersion());
        TestCase.assertEquals(parsed + "check incremental version", incremental, productVersion.getIncrementalVersion());
        TestCase.assertEquals(parsed + "check build number", buildnumber, productVersion.getBuildNumber());
        TestCase.assertEquals(parsed + "check qualifier", qualifier, productVersion.getQualifier());
        TestCase.assertEquals("check " + version + " string value", version, productVersion.toString());
    }

    public void testVersionComparing() {
        assertVersionEqual("4.1.1", "4.1.1");
        assertVersionOlder( "4.1.2", "4.1.3" );
        assertVersionOlder( "4.1.0-alpha", "4.1.0" );
        assertVersionOlder( "4.1.0-alpha", "4.1.0-alpha01" );
        assertVersionOlder( "4.1.0-alpha01", "4.1.0-alpha02" );
        assertVersionOlder( "4.1.0-alpha", "4.1.0-beta01" );
        assertVersionOlder("4.1.0-alpha", "4.1.0-beta01");
        assertVersionOlder( "4.1.t","4.1.x");
        assertVersionOlder( "4.1.0","4.1.x");
        assertVersionOlder("4.1.x", "4.2.0");
        assertVersionOlder("4.1.0", "4.1.x-YYYY-ZZZZZ");
        assertVersionEqual("4.1.0-SNAPSHOT", "4.1.0-SNAPSHOT");
        assertVersionOlder( "4.1.0-SNAPSHOT", "4.1.1-SNAPSHOT" );
        assertVersionOlder( "4.1.1-alpha01-SNAPSHOT", "4.1.1-SNAPSHOT" );
        assertVersionOlder( "4.1.1-alpha01-SNAPSHOT", "4.1.1-alpha02-SNAPSHOT" );
        assertVersionOlder("4.1.1-cp01-SNAPSHOT", "4.1.1-SNAPSHOT");
        assertVersionOlder("4.1.1-cp01-SNAPSHOT", "4.1.1-cp02-SNAPSHOT");
        assertVersionOlder( "4.1.1-SNAPSHOT" ,"4.1.1-plf-SNAPSHOT");

    }

    private void assertVersionOlder( String oldVersion, String newVersion ) {
        TestCase.assertTrue(oldVersion + " should be older than " + newVersion, newArtifactVersion(oldVersion).compareTo(newArtifactVersion(newVersion)) < 0);
        TestCase.assertTrue(newVersion + " should be newer than " + oldVersion, newArtifactVersion(newVersion).compareTo(newArtifactVersion(oldVersion)) > 0);
    }

    private void assertVersionEqual( String oldVersion, String newVersion ) {
        TestCase.assertTrue(oldVersion + " should be equal to " + newVersion, newArtifactVersion(oldVersion).compareTo(newArtifactVersion(newVersion)) == 0);
        TestCase.assertTrue(newVersion + " should be equal to " + oldVersion, newArtifactVersion(newVersion).compareTo(newArtifactVersion(oldVersion)) == 0);
    }


}
