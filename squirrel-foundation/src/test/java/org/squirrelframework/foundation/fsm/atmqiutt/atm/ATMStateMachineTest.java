package org.squirrelframework.foundation.fsm.atmqiutt.atm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.squirrelframework.foundation.component.SquirrelPostProcessorProvider;
import org.squirrelframework.foundation.fsm.ConverterProvider;
import org.squirrelframework.foundation.fsm.StateMachineBuilder;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.StateMachineStatus;
import org.squirrelframework.foundation.fsm.atmqiutt.atm.ATMStateMachine;
import org.squirrelframework.foundation.fsm.atmqiutt.atm.ATMStateMachine.ATMState;

public class ATMStateMachineTest {
    
    @AfterClass
    public static void afterTest() {
        ConverterProvider.INSTANCE.clearRegistry();
        SquirrelPostProcessorProvider.getInstance().clearRegistry();
    }
    
    private ATMStateMachine stateMachine;

    @Before
    public void setup() {
        //状态机注册
        StateMachineBuilder<ATMStateMachine, ATMState, String, Integer> builder = StateMachineBuilderFactory.create(
                ATMStateMachine.class, ATMState.class, String.class, Integer.class);
        builder.externalTransition().from(ATMState.Idle).to(ATMState.Loading).on("Connected").callMethod("doSomething");
        builder.externalTransition().from(ATMState.Loading).to(ATMState.Disconnected).on("ConnectionClosed");
        builder.externalTransition().from(ATMState.Loading).to(ATMState.InService).on("LoadSuccess");
        builder.externalTransition().from(ATMState.Loading).to(ATMState.OutOfService).on("LoadFail");
        builder.externalTransition().from(ATMState.OutOfService).to(ATMState.Disconnected).on("ConnectionLost");
        builder.externalTransition().from(ATMState.OutOfService).to(ATMState.InService).on("Startup");
        builder.externalTransition().from(ATMState.InService).to(ATMState.OutOfService).on("Shutdown");
        builder.externalTransition().from(ATMState.InService).to(ATMState.Disconnected).on("ConnectionLost");
        builder.externalTransition().from(ATMState.Disconnected).to(ATMState.InService).on("ConnectionRestored");
        
        stateMachine = builder.newStateMachine(ATMState.Idle);
    }
    
    @After
    public void teardown() {
        if(stateMachine!=null && stateMachine.getStatus()!=StateMachineStatus.TERMINATED) {
            stateMachine.terminate(null);
        }
    }
    
    @Test
    public void testIdelToInService() {
        stateMachine.start(1);
        
//        stateMachine.fire("Connected",2);
//
//        stateMachine.fire("LoadSuccess",3);
//
//        stateMachine.fire("Shutdown",4);
//
//        stateMachine.fire("ConnectionLost",5);
//
//        stateMachine.fire("ConnectionRestored",6);
    }

}
