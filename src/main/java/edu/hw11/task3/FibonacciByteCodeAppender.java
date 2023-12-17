package edu.hw11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public class FibonacciByteCodeAppender implements ByteCodeAppender {
    private static final String CLASS_NAME = "Fibonacci";
    private static final String METHOD_NAME = "getFibonacci";
    private static final String METHOD_SIGNATURE = "(I)J";
    private static final int STACK_SIZE = 5;

    @Override
    public Size apply(
        MethodVisitor mv,
        Implementation.Context context,
        MethodDescription methodDescription
    ) {
        Label l1 = new Label();

        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, l1);

        mv.visitInsn(Opcodes.LCONST_1);
        mv.visitInsn(Opcodes.LRETURN);

        mv.visitLabel(l1);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, METHOD_SIGNATURE, false);

        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, METHOD_SIGNATURE, false);

        mv.visitInsn(Opcodes.LADD);
        mv.visitInsn(Opcodes.LRETURN);

        return new Size(STACK_SIZE, 0);
    }
}
