package org.squirrelframework.foundation.fsm.atmqiutt.atm;

import lombok.extern.slf4j.Slf4j;
import org.squirrelframework.foundation.fsm.atmqiutt.atm.ATMStateMachine.ATMState;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

@Slf4j
public class ATMStateMachine extends AbstractStateMachine<ATMStateMachine, ATMState, String, Integer> {
    
    void postConstruct() {
        System.out.println("ATMStateMachine PostConstruct Touched!");
    }
    
    public enum ATMState {
        Idle, Loading, OutOfService, Disconnected, InService
    }
    
    private StringBuilder logger = new StringBuilder();

    private String sysLogger=new String();

    
    public void entryIdle(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("entryIdle");
        sysLogger="context:"+context+",entryIdle";
        System.out.println(sysLogger);
        log.info("context:[{}],entryIdle",context);
    }
    
    public void exitIdle(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("exitIdle");
        log.info("exitIdle");
        sysLogger="context:"+context+",exitIdle";
        System.out.println(sysLogger);

    }

    public void doSomething(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("doSomething");
        log.info("doSomething");
        sysLogger="context:"+context+",doSomething";
        System.out.println(sysLogger);

    }
    
    public void transitFromIdleToLoadingOnConnected(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromIdleToLoadingOnConnected");
        log.info("transitFromIdleToLoadingOnConnected");
        sysLogger="context:"+context+",transitFromIdleToLoadingOnConnected";
        System.out.println(sysLogger);

    }
    
    public void entryLoading(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("entryLoading");
        log.info("entryLoading");
        sysLogger="context:"+context+",entryLoading";
        System.out.println(sysLogger);
        if(context==2){
            this.fire("ConnectionClosed",context);
        }

    }
    
    public void exitLoading(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("exitLoading");
        log.info("exitLoading");

        sysLogger="context:"+context+",exitLoading";
        System.out.println(sysLogger);

    }
    
    public void transitFromLoadingToInServiceOnLoadSuccess(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromLoadingToInServiceOnLoadSuccess");
        log.info("transitFromLoadingToInServiceOnLoadSuccess");
        sysLogger="context:"+context+",transitFromLoadingToInServiceOnLoadSuccess";
        System.out.println(sysLogger);
    }
    
    public void transitFromLoadingToOutOfServiceOnLoadFail(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromLoadingToOutOfServiceOnLoadFail");
        log.info("transitFromLoadingToOutOfServiceOnLoadFail");
        sysLogger="context:"+context+",transitFromLoadingToOutOfServiceOnLoadFail";
        System.out.println(sysLogger);

    }
    
    public void transitFromLoadingToDisconnectedOnConnectionClosed(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromLoadingToDisconnectedOnConnectionClosed");
        log.info("transitFromLoadingToDisconnectedOnConnectionClosed");

        sysLogger="context:"+context+",transitFromLoadingToDisconnectedOnConnectionClosed";
        System.out.println(sysLogger);

    }
    
    public void entryOutOfService(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("entryOutOfService");
        log.info("entryOutOfService");
        sysLogger="context:"+context+",entryOutOfService";
        System.out.println(sysLogger);

    }
    
    public void transitFromOutOfServiceToDisconnectedOnConnectionLost(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromOutOfServiceToDisconnectedOnConnectionLost");
        log.info("transitFromOutOfServiceToDisconnectedOnConnectionLost");

        sysLogger="context:"+context+",transitFromOutOfServiceToDisconnectedOnConnectionLost";
        System.out.println(sysLogger);

    }
    
    public void transitFromOutOfServiceToInServiceOnStartup(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromOutOfServiceToInServiceOnStartup");
        log.info("transitFromOutOfServiceToInServiceOnStartup");
        sysLogger="context:"+context+",transitFromOutOfServiceToInServiceOnStartup";
        System.out.println(sysLogger);

    }
    
    public void exitOutOfService(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("exitOutOfService");
        log.info("exitOutOfService");

        sysLogger="context:"+context+",exitOutOfService";
        System.out.println(sysLogger);

    }
    
    public void entryDisconnected(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("entryDisconnected");
        log.info("entryDisconnected");

        sysLogger="context:"+context+",entryDisconnected";
        System.out.println(sysLogger);

    }
    
    public void transitFromDisconnectedToInServiceOnConnectionRestored(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromDisconnectedToInServiceOnConnectionRestored");
        log.info("transitFromDisconnectedToInServiceOnConnectionRestored");
        sysLogger="context:"+context+",transitFromDisconnectedToInServiceOnConnectionRestored";
        System.out.println(sysLogger);

    }
    
    public void exitDisconnected(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("exitDisconnected");
        log.info("exitDisconnected");
        sysLogger="context:"+context+",exitDisconnected";
        System.out.println(sysLogger);

    }
    
    public void entryInService(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("entryInService");
        log.info("entryInService");
        sysLogger="context:"+context+",entryInService";
        System.out.println(sysLogger);

    }
    
    public void transitFromInServiceToOutOfServiceOnShutdown(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromInServiceToOutOfServiceOnShutdown");
        log.info("transitFromInServiceToOutOfServiceOnShutdown");

        sysLogger="context:"+context+",transitFromInServiceToOutOfServiceOnShutdown";
        System.out.println(sysLogger);

    }
    
    public void transitFromInServiceToDisconnectedOnConnectionLost(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("transitFromInServiceToDisconnectedOnConnectionLost");
        log.info("transitFromInServiceToDisconnectedOnConnectionLost");
        sysLogger="context:"+context+",transitFromInServiceToDisconnectedOnConnectionLost";
        System.out.println(sysLogger);

    }
    
    public void exitInService(ATMState from, ATMState to, String event,Integer context) {
        addOptionalDot();
        logger.append("exitInService");
        log.info("exitInService");


        sysLogger="context:"+context+",exitInService";
        System.out.println(sysLogger);

    }
    
    private void addOptionalDot() {
        if (logger.length() > 0) {
            logger.append('.');
        }
    }
    
    public String consumeLog() {
        final String result = logger.toString();
        logger = new StringBuilder();
        return result;
    }
}
