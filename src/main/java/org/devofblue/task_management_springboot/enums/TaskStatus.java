package org.devofblue.task_management_springboot.enums;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    IN_REVIEW,
    DONE;

    public boolean canTransitionTo(TaskStatus next) {
        return next.ordinal() == this.ordinal() + 1;
    }
}
