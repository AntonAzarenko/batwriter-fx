package com.azarenka.batwriter.domain;

public enum TypeCommand {
    START_APPLICATION {
        public  String getType() {
            return "Starting to";
        }
    },
    CHANGE_DIR{
        public String getType(){
            return "Relocating to ";
        }
    },
    SYSTEM_VARIABLE {
        @Override
        public String getType() {
            return "System var";
        }
    };

    public abstract String getType();
}
