package me.kehycs.javap.accessflag;

import me.kehycs.javap.exception.ClassFileParseException;

/**
 * Created by kehanyang on 12/30/15.
 */
public class ClassAccessFlag extends AccessFlag {

    public ClassAccessFlag(int flags) throws ClassFileParseException {
        super(flags);
    }

}
