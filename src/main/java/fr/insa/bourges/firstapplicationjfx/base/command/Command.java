package fr.insa.bourges.firstapplicationjfx.base.command;

@FunctionalInterface
public interface Command {
    void execute(Object... args);
}
