package com.function;

import java.time.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

public class TimerTriggerJava1 {
   
    @FunctionName("TimerTriggerJava1")
    public void run(
        @TimerTrigger(name = "timerInfo", schedule = "0 */5 * * * *") String timerInfo,
        final ExecutionContext context
    ) {
        context.getLogger().info("Hello word! " + LocalDateTime.now());
    }
}