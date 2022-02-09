package com.webpage.predictpoliticalpartyprice.entities;



import java.time.LocalDateTime;

public abstract class Log{

    public abstract LocalDateTime getTimestamp();

    public abstract double getLogvalue();
}
