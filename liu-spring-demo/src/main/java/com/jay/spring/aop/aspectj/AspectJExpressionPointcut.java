package com.jay.spring.aop.aspectj;

import com.jay.spring.aop.MethodMatcher;
import com.jay.spring.aop.Pointcut;
import com.jay.spring.util.ClassUtils;
import com.jay.spring.util.StringUtils;
import org.aspectj.weaver.reflect.ReflectionWorld;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParameter;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiang.wei on 2018/7/30
 *
 * @author xiang.wei
 */
public class AspectJExpressionPointcut implements Pointcut,MethodMatcher {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();


    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }
    private String expression;

    private  PointcutExpression pointcutExpression;


    private ClassLoader pointcutClassLoader;

    public AspectJExpressionPointcut(){

    }
    public void setExpression(String expression) {
        this.expression = expression;
    }


    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }


    @Override
    public boolean matches(Method method) {
        checkReadyToMatch();

        ShadowMatch shadowMatch = getShadowMatch(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        }
        return false;
    }

    private ShadowMatch getShadowMatch(Method method) {

        ShadowMatch shadowMatch = null;
        try {
            //匹配对应的方法
            shadowMatch = this.pointcutExpression.matchesMethodExecution(method);
        } catch (ReflectionWorld.ReflectionWorldException e) {
            throw new RuntimeException("not implemented yet");
        }

        return shadowMatch;
    }


    private void checkReadyToMatch() {
        if (getExpression() == null) {
            throw new IllegalStateException("Must set property 'expression' before attempting to match");
        }
        if (this.pointcutExpression == null) {
            //加载类
            this.pointcutClassLoader = ClassUtils.getDefaultClassLoader();
            this.pointcutExpression = buildPointcutException(this.pointcutClassLoader);

        }
    }

    private PointcutExpression buildPointcutException(ClassLoader classLoader) {
                 PointcutParser parser= PointcutParser.
                getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, classLoader);
        return parser.parsePointcutExpression(replaceBooleanOperators(getExpression()),
                null, new PointcutParameter[0]);
    }

    private String replaceBooleanOperators(String pcExpr) {
        String result = StringUtils.replace(pcExpr, " and ", " && ");
        result = StringUtils.replace(result, " or ", " || ");
        result = StringUtils.replace(result, " not ", " ! ");
        return result;
    }

}

