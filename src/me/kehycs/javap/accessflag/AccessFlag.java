package me.kehycs.javap.accessflag;

import me.kehycs.javap.exception.ClassFileParseException;
import me.kehycs.javap.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kehanyang on 12/29/15.
 */
public class AccessFlag {

    public static final int PUBLIC = 0x0001;
    public static final int PRIVATE = 0x0002;
    public static final int PROTECTED = 0x0004;
    public static final int STATIC = 0x0008;
    public static final int FINAL = 0x0010;
    public static final int SUPER = 0x0020;
    public static final int VOLATILE = 0x0040;
    public static final int TRANSIENT = 0x0080;
    public static final int INTERFACE = 0x0200;
    public static final int ABSTRACT = 0x0400;
    public static final int SYNTHETIC = 0x1000;
    public static final int ANNOTATION = 0x2000;
    public static final int ENUM = 0x4000;

    public static final int UNDEFINED_FLAGS = 0x8900;

    private static final List<Pair<Integer, String>> accessFlagName = new ArrayList<>();

    static {
        accessFlagName.add(new Pair<>(PUBLIC, "ACC_PUBLIC"));
        accessFlagName.add(new Pair<>(PRIVATE, "ACC_PRIVATE"));
        accessFlagName.add(new Pair<>(PROTECTED, "ACC_PROTECTED"));
        accessFlagName.add(new Pair<>(STATIC, "ACC_STATIC"));
        accessFlagName.add(new Pair<>(FINAL, "ACC_FINAL"));
        accessFlagName.add(new Pair<>(SUPER, "ACC_SUPER"));
        accessFlagName.add(new Pair<>(VOLATILE, "ACC_VOLATILE"));
        accessFlagName.add(new Pair<>(TRANSIENT, "ACC_TRANSIENT"));
        accessFlagName.add(new Pair<>(INTERFACE, "ACC_INTERFACE"));
        accessFlagName.add(new Pair<>(ABSTRACT, "ACC_ABSTRACT"));
        accessFlagName.add(new Pair<>(SYNTHETIC, "ACC_SYNTHETIC"));
        accessFlagName.add(new Pair<>(ANNOTATION, "ACC_ANNOTATION"));
        accessFlagName.add(new Pair<>(ENUM, "ACC_ENUM"));
    }

    private int flags;

    public AccessFlag(int flags) throws ClassFileParseException {
        if ((flags & UNDEFINED_FLAGS) != 0) {
            throw new ClassFileParseException("Unknown access flag.");
        }
        this.flags = flags;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("flags: ");
        boolean firstFlag = true;
        for (Pair<Integer, String> flag : accessFlagName) {
            int flagCode = flag.first;
            if ((flags & flagCode) != 0) {
                if (firstFlag) {
                    firstFlag = false;
                } else {
                    result.append(", ");
                }
                result.append(flag.second);
            }
        }
        return result.toString();
    }

    public String getClassModifiers() {
        StringBuilder result = new StringBuilder();
        if (isPublic()) {
            result.append("public ");
        }
        if (isFinal()) {
            result.append("final ");
        }
        if (isInterface()) {
            result.append("interface ");
        } else if (isAnnotation()) {
            result.append("@interface ");
        } else if (isEnum()) {
            result.append("enum ");
        } else {
            if (isAbstract()) {
                result.append("abstract ");
            }
            result.append("class ");
        }
        return result.toString();
    }

    public String getFieldModifiers() {
        StringBuilder result = new StringBuilder();
        if (isPublic()) {
            result.append("public ");
        } else if (isPrivate()) {
            result.append("private ");
        } else if (isProtected()) {
            result.append("protected ");
        }
        if (isStatic()) {
            result.append("static ");
        }
        if (isFinal()) {
            result.append("final ");
        } else if (isVolatile()) {
            result.append("volatile ");
        }
        if (isTransient()) {
            result.append("transient ");
        }
        return result.toString();
    }

    public boolean isPublic() {
        return (flags & PUBLIC) != 0;
    }

    public boolean isPrivate() {
        return (flags & PRIVATE) != 0;
    }

    public boolean isProtected() {
        return (flags & PROTECTED) != 0;
    }

    public boolean isStatic() {
        return (flags & STATIC) != 0;
    }

    public boolean isFinal() {
        return (flags & FINAL) != 0;
    }

    public boolean isSuper() {
        return (flags & SUPER) != 0;
    }

    public boolean isVolatile() {
        return (flags & VOLATILE) != 0;
    }

    public boolean isTransient() {
        return (flags & TRANSIENT) != 0;
    }

    public boolean isInterface() {
        return (flags & INTERFACE) != 0;
    }

    public boolean isAbstract() {
        return (flags & ABSTRACT) != 0;
    }

    public boolean isSynthetic() {
        return (flags & SYNTHETIC) != 0;
    }

    public boolean isAnnotation() {
        return (flags & ANNOTATION) != 0;
    }

    public boolean isEnum() {
        return (flags & ENUM) != 0;
    }

}
