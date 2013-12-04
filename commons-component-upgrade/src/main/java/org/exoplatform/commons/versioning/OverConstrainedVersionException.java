package org.exoplatform.commons.versioning;

import org.exoplatform.commons.exceptions.AbstractProductException;

import org.exoplatform.commons.metadata.Product;

/**
 * Created with IntelliJ IDEA.
 * User: menzli
 * Date: 04/12/13
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class OverConstrainedVersionException extends AbstractProductException {

    public OverConstrainedVersionException( String msg,Product product ) {
        super(msg, product);
    }
}
