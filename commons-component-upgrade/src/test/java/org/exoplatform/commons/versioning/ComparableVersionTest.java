package org.exoplatform.commons.versioning;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: menzli
 * Date: 04/12/13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class ComparableVersionTest extends TestCase {

    private Comparable newComparable( String version )
    {
        return new ComparableVersion( version );
    }

    private static final String[] VERSIONS_QUALIFIER =
        { "1-alpha2snapshot", "1-alpha2", "1-alpha-123", "1-beta-2", "1-beta123", "1-m2", "1-m11", "1-rc", "1-cr2",
                "1-rc123", "1-cp" , "1-SNAPSHOT", "1", "1-sp", "1-sp2", "1-sp123", "1-abc", "1-def", "1-pom-1", "1-1-snapshot",
                "1-1", "1-2", "1-123" };

    private static final String[] VERSIONS_NUMBER =
        { "2.0", "2-1", "2.0.a", "2.0.0.a", "2.0.2", "2.0.123", "2.1.0", "2.1-a", "2.1b", "2.1-c", "2.1-1", "2.1.0.1",
                "2.2", "2.123", "11.a2", "11.a11", "11.b2", "11.b11", "11.m2", "11.m11", "11", "11.a", "11b", "11c", "11m" };

    private void checkVersionsOrder( String[] versions ) {
        Comparable[] c = new Comparable[versions.length];
        for ( int i = 0; i < versions.length; i++ )
        {
            c[i] = newComparable( versions[i] );
        }

        for ( int i = 1; i < versions.length; i++ )
        {
            Comparable low = c[i - 1];
            for ( int j = i; j < versions.length; j++ )
            {
                Comparable high = c[j];
                TestCase.assertTrue("expected " + low + " < " + high, low.compareTo(high) < 0);
                TestCase.assertTrue("expected " + high + " > " + low, high.compareTo(low) > 0);
            }
        }
    }

    private void checkVersionsEqual( String v1, String v2 )
    {
        Comparable c1 = newComparable( v1 );
        Comparable c2 = newComparable( v2 );
        TestCase.assertTrue("expected " + v1 + " == " + v2, c1.compareTo(c2) == 0);
        TestCase.assertTrue("expected " + v2 + " == " + v1, c2.compareTo(c1) == 0);
        TestCase.assertTrue("expected same hashcode for " + v1 + " and " + v2, c1.hashCode() == c2.hashCode());
        TestCase.assertTrue("expected " + v1 + ".equals( " + v2 + " )", c1.equals(c2));
        TestCase.assertTrue("expected " + v2 + ".equals( " + v1 + " )", c2.equals(c1));
    }

    private void checkVersionsOrder( String v1, String v2 ) {
        Comparable c1 = newComparable( v1 );
        Comparable c2 = newComparable( v2 );
        TestCase.assertTrue("expected " + v1 + " < " + v2, c1.compareTo(c2) < 0);
        TestCase.assertTrue("expected " + v2 + " > " + v1, c2.compareTo(c1) > 0);
    }
    public void testVersionsQualifier() {
        checkVersionsOrder( VERSIONS_QUALIFIER );
    }

    public void testVersionsNumber() {
        checkVersionsOrder( VERSIONS_NUMBER );
    }
    public void testVersionsEqual() {
        checkVersionsEqual( "1", "1" );
        checkVersionsEqual( "1", "1.0" );
        checkVersionsEqual( "1", "1.0.0" );
        checkVersionsEqual( "1.0", "1.0.0" );
        checkVersionsEqual( "1", "1-0" );
        checkVersionsEqual( "1", "1.0-0" );
    }
}
