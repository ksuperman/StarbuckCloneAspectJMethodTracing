package aspects;

aspect TraceMethodExecution {
		
	String temp;
	
	MethodTracer executionTracer = new MethodTracer();	
	
		pointcut controlPointcut() : (execution(* *(..)) && !cflow(within(TraceMethodExecution)||within(MethodTracer)||within(MethodInstance)));
		
		before() : controlPointcut() {
				
			String jointPointLongString = thisJoinPoint.toLongString();
			String className = thisJoinPoint.getStaticPart().getSourceLocation().getWithinType().getName();
			String returntype = thisJoinPoint.getSignature().toString().split(" ")[0];
			String methodName = thisJoinPoint.getStaticPart().getSignature().getName();
			String methodModifiers = java.lang.reflect.Modifier.toString(thisJoinPoint.getStaticPart().getSignature().getModifiers());
			Object[] methodparameters = thisJoinPoint.getArgs();
			
			executionTracer.addInstance(jointPointLongString,className,methodName,methodModifiers,methodparameters,returntype);
			
		}
		
		after() returning() : controlPointcut(){
			
			String jointPointLongString = thisJoinPoint.toLongString();
			String className = thisJoinPoint.getStaticPart().getSourceLocation().getWithinType().getName();
			String methodName = thisJoinPoint.getStaticPart().getSignature().getName();
			String returntype = thisJoinPoint.getSignature().toString().split(" ")[0];
			String methodModifiers = java.lang.reflect.Modifier.toString(thisJoinPoint.getStaticPart().getSignature().getModifiers());
			Object[] methodparameters = thisJoinPoint.getArgs();
			executionTracer.removeInstance(jointPointLongString,className,methodName,methodModifiers,methodparameters,returntype);
		}
			
	}

