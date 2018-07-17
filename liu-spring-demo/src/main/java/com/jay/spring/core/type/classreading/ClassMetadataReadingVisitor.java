package com.jay.spring.core.type.classreading;

import com.jay.spring.util.ClassUtils;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.SpringAsmInfo;

/**
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 */
public class ClassMetadataReadingVisitor  extends ClassVisitor{
    private String className;

    private boolean isInterface;

    private boolean isAbstract;

    private boolean isFinal;

    private String superClassName;

    private String[] interfaces;

    public ClassMetadataReadingVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }


    public ClassMetadataReadingVisitor(int i) {
        super(i);
    }

    public ClassMetadataReadingVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    /**
     * @param version
     * @param access
     * @param name
     * @param signature
     * @param supername
     * @param interfaces
     *
     *
     * 这个涉及到了Java的字节码， 在Java字节码中，常量池结束之后，
     * 有两个字节表示访问标识(access_flags)，
     * 这个标识用于识别一些类或者接口层次的访问信息，
     * 例如这个Class是类或者接口，是否为public ,abstract ,final 等等
     * 对类的处理，判断类的基本情况
     *
     */
    //
    @Override
    public void visit(int version, int access, String name, String signature, String supername, String[] interfaces) {
        this.className = ClassUtils.convertResourcePathToClassName(name);
        this.isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
        this.isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
        this.isFinal = ((access & Opcodes.ACC_FINAL) != 0);
        if (supername != null) {
            this.superClassName = ClassUtils.convertResourcePathToClassName(supername);
        }
        this.interfaces = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public String[] getInterfaceNames() {
        return interfaces;
    }

    public void setInterfaces(String[] interfaces) {
        this.interfaces = interfaces;
    }
}
