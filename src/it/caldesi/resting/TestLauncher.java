package it.caldesi.resting;

import it.caldesi.resting.exceptions.AssertionFailedException;
import it.caldesi.resting.sample.SampleTestCase;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Domenico Rosario Caldesi, <d.r.caldesi@gmail.com>
 */
public class TestLauncher {

    public static void executeTest(Class aClass) throws Exception {
        Object instance = aClass.newInstance();
        if (!(instance instanceof TestCase))
            throw new Exception("test to execute must be a TestCase subclass!");

        Method[] methods = aClass.getMethods();
        boolean refreshForEachFuction;
        try{
            Field refreshForEachFuctionField = aClass.getDeclaredField("refreshForEachFuction");
            refreshForEachFuctionField.setAccessible(true);
            refreshForEachFuction = refreshForEachFuctionField.getBoolean(instance);
        }catch(NoSuchFieldException e){
            refreshForEachFuction = false;
        }

        if (!refreshForEachFuction)
            invokeSetupMethod(methods, instance);
        for (Method method : methods) {
            if (!Modifier.isPrivate(method.getModifiers()) && method.getName().toUpperCase().startsWith("TEST")) {
                try {
                    if (refreshForEachFuction)
                        invokeSetupMethod(methods, instance);
                    method.invoke(instance);
                    handleSuccess(method.getName());
                } catch (InvocationTargetException ite) {
                    Throwable cause = ite.getCause();
                    if (cause != null)
                        if (cause instanceof AssertionFailedException)
                            handleAssertionFailed(method.getName(), (AssertionFailedException) cause);
                        else
                            handleException(method.getName(), ite);
                } catch (Exception e) {
                    handleException(method.getName(), e);
                } finally {
                    if (refreshForEachFuction)
                        invokeCleanupMethod(methods, instance);
                }
            }
        }
        if (!refreshForEachFuction)
            invokeCleanupMethod(methods, instance);
    }

    private static void invokeSetupMethod(Method[] methods, Object instance) {
        for (Method method : methods) {
            if (method.getName() == "setUp") {
                try {
                    method.invoke(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void invokeCleanupMethod(Method[] methods, Object instance) {
        for (Method method : methods) {
            if (method.getName() == "cleanUp") {
                try {
                    method.invoke(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // HANDLERS FOR TEST RESULTS

    private static void handleAssertionFailed(String methodName, AssertionFailedException afe) {
        int lineNumber = afe.getStackTrace()[1].getLineNumber();
        System.out.println("Assertion failed (@line " + lineNumber + ") on method: " + methodName + "()");
        System.out.println("\t" + afe.getMessage());
    }

    private static void handleException(String methodName, Exception e) {
        System.out.println(methodName + "() failed");
        e.printStackTrace();
    }

    private static void handleSuccess(String methodName) {
        System.out.println(methodName + "() passed");
    }

    // EXAMPLE MAIN
    public static void main(String[] args) throws Exception {
        executeTest(SampleTestCase.class);
    }

}