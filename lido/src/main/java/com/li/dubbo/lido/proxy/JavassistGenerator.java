/**
 * 
 */
package com.li.dubbo.lido.proxy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.CtField.Initializer;
/**
 * @author lipeng
 *
 * 2016年5月13日
 */
public class JavassistGenerator {
	public static void main(String[] args) throws CannotCompileException, NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        // 创建类
        ClassPool pool = ClassPool.getDefault();
        CtClass cls = pool.makeClass("com.li.dubbo.lido.TestClass");

        // 添加私有成员name及其getter、setter方法
        CtField param = new CtField(pool.get("java.lang.String"), "name", cls);
        param.setModifiers(Modifier.PRIVATE);
        cls.addMethod(CtNewMethod.setter("setName", param));
        cls.addMethod(CtNewMethod.getter("getName", param));
        cls.addField(param, Initializer.constant(""));

        // 添加无参的构造体
        CtConstructor cons = new CtConstructor(new CtClass[] {}, cls);
        cons.setBody("{name = \"Richard\";}");
        cls.addConstructor(cons);

        // 添加有参的构造体
        cons = new CtConstructor(new CtClass[] {pool.get("java.lang.String")}, cls);
        cons.setBody("{$0.name = $1;}");
        cls.addConstructor(cons);

        // 打印创建类的类名
        System.out.println(cls.toClass());

        // 通过反射创建无参的实例，并调用getName方法
        Object o = Class.forName("com.li.dubbo.lido.TestClass").newInstance();
        Method getter = o.getClass().getMethod("getName");
        System.out.println(getter.invoke(o));

        // 调用其setName方法
        Method setter = o.getClass().getMethod("setName", new Class[] {String.class});
        setter.invoke(o, "Richard");
        System.out.println(getter.invoke(o));

        // 通过反射创建有参的实例，并调用getName方法
        o = Class.forName("com.li.dubbo.lido.TestClass").getConstructor(String.class).newInstance("Li ling");
        getter = o.getClass().getMethod("getName");
        System.out.println(getter.invoke(o));
    }
}