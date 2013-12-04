package org.exoplatform.commons.versioning;

/**
 * Created with IntelliJ IDEA.
 * User: menzli
 * Date: 04/12/13
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class Restriction {
    private final ProductVersion lowerBound;

    private final boolean lowerBoundInclusive;

    private final ProductVersion upperBound;

    private final boolean upperBoundInclusive;

    public static final Restriction EVERYTHING = new Restriction( null, false, null, false );

    public Restriction( ProductVersion lowerBound, boolean lowerBoundInclusive, ProductVersion upperBound,boolean upperBoundInclusive ) {
        this.lowerBound = lowerBound;
        this.lowerBoundInclusive = lowerBoundInclusive;
        this.upperBound = upperBound;
        this.upperBoundInclusive = upperBoundInclusive;
    }

    public ProductVersion getLowerBound() {
        return lowerBound;
    }

    public boolean isLowerBoundInclusive() {
        return lowerBoundInclusive;
    }

    public ProductVersion getUpperBound() {
        return upperBound;
    }

    public boolean isUpperBoundInclusive() {
        return upperBoundInclusive;
    }

    public boolean containsVersion( ProductVersion version ) {
        if ( lowerBound != null )
        {
            int comparison = lowerBound.compareTo( version );

            if ( ( comparison == 0 ) && !lowerBoundInclusive )
            {
                return false;
            }
            if ( comparison > 0 )
            {
                return false;
            }
        }
        if ( upperBound != null )
        {
            int comparison = upperBound.compareTo( version );

            if ( ( comparison == 0 ) && !upperBoundInclusive )
            {
                return false;
            }
            if ( comparison < 0 )
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 13;

        if ( lowerBound == null )
        {
            result += 1;
        }
        else
        {
            result += lowerBound.hashCode();
        }

        result *= lowerBoundInclusive ? 1 : 2;

        if ( upperBound == null )
        {
            result -= 3;
        }
        else
        {
            result -= upperBound.hashCode();
        }

        result *= upperBoundInclusive ? 2 : 3;

        return result;
    }

    @Override
    public boolean equals( Object other ) {
        if ( this == other )
        {
            return true;
        }

        if ( !( other instanceof Restriction ) )
        {
            return false;
        }

        Restriction restriction = (Restriction) other;
        if ( lowerBound != null )
        {
            if ( !lowerBound.equals( restriction.lowerBound ) )
            {
                return false;
            }
        }
        else if ( restriction.lowerBound != null )
        {
            return false;
        }

        if ( lowerBoundInclusive != restriction.lowerBoundInclusive )
        {
            return false;
        }

        if ( upperBound != null )
        {
            if ( !upperBound.equals( restriction.upperBound ) )
            {
                return false;
            }
        }
        else if ( restriction.upperBound != null )
        {
            return false;
        }

        return upperBoundInclusive == restriction.upperBoundInclusive;

    }

    public String toString() {
        StringBuilder buf = new StringBuilder();

        buf.append( isLowerBoundInclusive() ? "[" : "(" );
        if ( getLowerBound() != null )
        {
            buf.append( getLowerBound().toString() );
        }
        buf.append( "," );
        if ( getUpperBound() != null )
        {
            buf.append( getUpperBound().toString() );
        }
        buf.append( isUpperBoundInclusive() ? "]" : ")" );

        return buf.toString();
    }
}
