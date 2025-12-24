**Timer** is a facet (non-visual component) designed to run UI code at specified time intervals.
The timer action is initiated from the browser as a user request, and its handler can update UI components. The timer
stops working when the fragment it was created for is detached.

Timers are defined using the `timer` element. It has the following attributes:

- `delay` – defines timer interval in milliseconds;
- `autostart` – if set to `true`, the timer starts immediately after the fragment is attached. By
  default, the value is `false`, which means that the timer will start only when its `start()`
  method is invoked;
- `repeating` – turns on repeated execution of the timer. If the attribute is set to `true`,
  the timer runs in cycles at equal intervals defined in the `delay` attribute. Otherwise, the timer runs
  only once – `delay` milliseconds after the timer starts.

To execute code on timer, subscribe to its `TimerActionEvent` in the fragment controller.

See also <a href="/ui-samples/sample/delayed-data-load">the example</a> of how to use timer to delay a costly data
loading operation until the user has made the final choice of conditions.
