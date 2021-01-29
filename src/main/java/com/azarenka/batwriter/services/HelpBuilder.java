package com.azarenka.batwriter.services;

import com.azarenka.batwriter.domain.CommandToAppend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpBuilder {

    private CommandToAppend commandToAppend;

    public HelpBuilder(CommandToAppend commandToAppend) {
        this.commandToAppend = commandToAppend;
    }

    private boolean isHelp;
    private boolean endHelp;

    public List<String> buildDoc(List<String> document) {
        Map<List<String>, List<String>> map = findHelper(document);
        List<String> help = addToHelp();
        List<String> strings = new ArrayList<>();
        map.forEach((k, v) -> {
            if (v.size() > 0) {
                k.add("");
                v.addAll(1, help);
                v.addAll(0, k);
            } else {
                v.addAll(0, getNewHelpHeader(help));
                v.addAll(0, k);
            }
        });
        map.values().forEach(strings::addAll);

        return strings;
    }

    /**
     * Method receives Collection with strings of document. It split his to 2 collections - part of Help
     * and part of other commands. And return map with two collections
     *
     * @param document list string of document
     * @return map with to collection 1. collection with commands and other strings. 2. Collection with helper strings
     */
    public Map<List<String>, List<String>> findHelper(List<String> document) {
        List<String> helper = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        document.forEach(string -> {
            checkStartHelper(string);
            if (isHelp) {
                helper.add(string);
                checkEndHelper(string);
                if (endHelp) {
                    isHelp = false;
                    endHelp = false;
                }
            } else {
                commands.add(string);
            }
        });
        Map<List<String>, List<String>> map = new HashMap<>();
        map.put(commands, helper);
        return map;
    }

    private void checkStartHelper(String string) {
        if (string.equals("@if \"%1\"==\"--help\" (")) {
            isHelp = true;
        }
    }

    private void checkEndHelper(String string) {
        if (string.equals(")")) {
            endHelp = true;
        }
    }

    private List<String> getNewHelpHeader(List<String> body) {
        ArrayList<String> help = new ArrayList<>();
        help.add("@if \"%1\"==\"--help\" (");
        help.addAll(1, body);
        help.add(")");
        return help;
    }

    private List<String> addToHelp() {
        ArrayList<String> help = new ArrayList<>();
        help.add("\t@echo ----------------------------------------------");
        help.add("\t@echo " + commandToAppend.getTextCommand() + " " + commandToAppend.getDescription() + " " + commandToAppend.getPath());
        return help;
    }
}
