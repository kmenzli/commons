package org.exoplatform.commons.exceptions;

import org.exoplatform.commons.metadata.Product;


/**
 * Created with IntelliJ IDEA.
 * User: menzli
 * Date: 04/12/13
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class AbstractProductException  extends Exception {

    private String groupId;

    private String artifactId;

    private String version;

    private String type;

    private Product product;

    private final String originalMessage;

    static final String LS = System.getProperty( "line.separator" );

    protected AbstractProductException( String message,
                                         String groupId,
                                         String artifactId,
                                         String version,
                                         String type) {
        this(message, groupId, artifactId, version, type, null);
    }
    protected AbstractProductException( String message,
                                        String groupId,
                                        String artifactId,
                                        String version,
                                        String type,
                                        Throwable t ) {
        super(constructMessageBase(message, groupId, artifactId, version, type), t);
        this.originalMessage = message;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.type = type;
        this.version = version;

    }
    protected AbstractProductException( String message, Product product, Throwable t )
    {
        this( message, product.getGroupId(), product.getArtifactId(), product.getVersion(), product.getType(),t );
        this.product = product;
    }

    protected AbstractProductException( String message, Product product) {
        this( message, product, null );
    }

    private static String constructMessageBase( String message, String groupId, String artifactId, String version, String type ){
        StringBuilder sb = new StringBuilder();

        sb.append( message );

        if ( message == null) {
            sb.append( LS );
            sb.append( "  " + groupId + ":" + artifactId + ":" + type + ":" + version );
            sb.append( LS );
        }

        return sb.toString();
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getGroupId() {
        return groupId;
    }
}
