package org.exoplatform.commons.versioning;

import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: menzli
 * Date: 04/12/13
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class PlatformProductVersion implements ProductVersion {

    private Integer majorVersion;

    private Integer minorVersion;

    private Integer incrementalVersion;

    private Integer buildNumber;

    private String qualifier;

    private ComparableVersion comparable;

    public PlatformProductVersion(String version) {
        parseVersion( version );
    }

    @Override
    public int hashCode() {
        return 11 + comparable.hashCode();
    }

    @Override
    public boolean equals( Object other ) {
        if ( this == other )
        {
            return true;
        }

        if ( !( other instanceof ProductVersion ) )
        {
            return false;
        }

        return compareTo( (ProductVersion) other ) == 0;
    }
    public int compareTo( ProductVersion otherVersion )
    {
        if ( otherVersion instanceof PlatformProductVersion)
        {
            return this.comparable.compareTo( ( (PlatformProductVersion) otherVersion ).comparable );
        }
        else
        {
            return compareTo( new PlatformProductVersion( otherVersion.toString() ) );
        }
    }

    public int getMajorVersion()
    {
        return majorVersion != null ? majorVersion : 0;
    }

    public int getMinorVersion()
    {
        return minorVersion != null ? minorVersion : 0;
    }

    public int getIncrementalVersion()
    {
        return incrementalVersion != null ? incrementalVersion : 0;
    }

    public int getBuildNumber()
    {
        return buildNumber != null ? buildNumber : 0;
    }

    public String getQualifier()
    {
        return qualifier;
    }

    public final void parseVersion( String version )
    {
        comparable = new ComparableVersion( version );

        int index = version.indexOf( "-" );

        String part1;
        String part2 = null;

        if ( index < 0 )
        {
            part1 = version;
        }
        else
        {
            part1 = version.substring( 0, index );
            part2 = version.substring( index + 1 );
        }

        if ( part2 != null )
        {
            try
            {
                if ( ( part2.length() == 1 ) || !part2.startsWith( "0" ) )
                {
                    buildNumber = Integer.valueOf( part2 );
                }
                else
                {
                    qualifier = part2;
                }
            }
            catch ( NumberFormatException e )
            {
                qualifier = part2;
            }
        }

        if ( ( !part1.contains( "." ) ) && !part1.startsWith( "0" ) )
        {
            try
            {
                majorVersion = Integer.valueOf( part1 );
            }
            catch ( NumberFormatException e )
            {
                // qualifier is the whole version, including "-"
                qualifier = version;
                buildNumber = null;
            }
        }
        else
        {
            boolean fallback = false;

            StringTokenizer tok = new StringTokenizer( part1, "." );
            try
            {
                majorVersion = getNextIntegerToken( tok );
                if ( tok.hasMoreTokens() )
                {
                    minorVersion = getNextIntegerToken( tok );
                }
                if ( tok.hasMoreTokens() )
                {
                    incrementalVersion = getNextIntegerToken( tok );
                }
                if ( tok.hasMoreTokens() )
                {
                    fallback = true;
                }

                // string tokenzier won't detect these and ignores them
                if ( part1.contains( ".." ) || part1.startsWith( "." ) || part1.endsWith( "." ) )
                {
                    fallback = true;
                }
            }
            catch ( NumberFormatException e )
            {
                fallback = true;
            }

            if ( fallback )
            {
                // qualifier is the whole version, including "-"
                qualifier = version;
                majorVersion = null;
                minorVersion = null;
                incrementalVersion = null;
                buildNumber = null;
            }
        }
    }

    private static Integer getNextIntegerToken( StringTokenizer tok )
    {
        String s = tok.nextToken();
        if ( ( s.length() > 1 ) && s.startsWith( "0" ) )
        {
            throw new NumberFormatException( "Number part has a leading 0: '" + s + "'" );
        }
        return Integer.valueOf( s );
    }

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        if ( majorVersion != null )
        {
            buf.append( majorVersion );
        }
        if ( minorVersion != null )
        {
            buf.append( "." );
            buf.append( minorVersion );
        }
        if ( incrementalVersion != null )
        {
            buf.append( "." );
            buf.append( incrementalVersion );
        }
        if ( buildNumber != null )
        {
            buf.append( "-" );
            buf.append( buildNumber );
        }
        else if ( qualifier != null )
        {
            if ( buf.length() > 0 )
            {
                buf.append( "-" );
            }
            buf.append( qualifier );
        }
        return buf.toString();
    }

}
