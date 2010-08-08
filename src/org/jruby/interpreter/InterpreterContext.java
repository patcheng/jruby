package org.jruby.interpreter;

import org.jruby.runtime.Frame;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * Meant as a simple interface for all the various methods we will want
 * the interpreter to have without having to commit to many technical details.
 *
 * For example if we add primitive variable operations then we will need to
 * add setters which are unboxed.  The ability to get and set in multiple ways
 * will mean a proliferation of methods to support this without needing to
 * make huge changes to operands or instructions (other than calling the new
 * method on this context).
 *
 * This is also a interface by matter of moving forward.  This could all also
 * be right on InterpretedIRMethod, but by having an interface the compiler
 * can "cheat" initially and use the same context for some of the more difficult
 * parts until it finds the best way.
 *
 */
public interface InterpreterContext {
    // Section: Return value, Local Variables, Temporary Variables
    public Object getParameter(int offset);
    public Object getReturnValue();
    public void setReturnValue(Object returnValue);

    public Object getTemporaryVariable(int offset);
    public Object setTemporaryVariable(int offset, Object value);
    public Object getLocalVariable(int location);
    public Object setLocalVariable(int location, Object value);

    public Object getSelf();
    
    // Section: Runtime helpers
    public ThreadContext getContext();

    public void setFrame(Frame currentFrame);
    public Frame getFrame();

    public IRubyObject[] getParametersFrom(int argIndex);
}