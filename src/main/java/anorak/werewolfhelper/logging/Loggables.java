package anorak.werewolfhelper.logging;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Loggables {
    public static ILoggableResult fromBoolean(boolean value) {
        return new ILoggableResult() {
            @Override
            public String getInputString() {
                return value ? "true" : "false";
            }

            @Override
            public String getPrettyString() {
                return value ? "TRUE" : "FALSE";
            }
        };
    }

    public static ILoggableResult fromString(String value) {
        return new ILoggableResult() {
            @Override
            public String getInputString() {
                return value;
            }

            @Override
            public String getPrettyString() {
                return value;
            }
        };
    }

    public static ILoggableResult fromInt(Integer value) {
        return new ILoggableResult() {
            @Override
            public String getInputString() {
                return String.valueOf(value);
            }

            @Override
            public String getPrettyString() {
                return String.valueOf(value);
            }
        };
    }

    public static ILoggableResult fromRole(Role value) {
        return new ILoggableResult() {
            @Override
            public String getInputString() {
                return value.getName();
            }

            @Override
            public String getPrettyString() {
                return value.toString();
            }
        };
    }

    public static ILoggableResult fromPlayer(Player value) {
        return new ILoggableResult() {
            @Override
            public String getInputString() {
                return value.getName();
            }

            @Override
            public String getPrettyString() {
                return value.toString();
            }
        };
    }

    public static <T> ILoggableResult fromList(List<T> list, Function<T, ILoggableResult> function) {
        List<ILoggableResult> loggablesList = new ArrayList<>(list.stream().map(function).toList());

        return new ILoggableResult() {
            @Override
            public String getInputString() {
                return loggablesList.stream().map(ILoggableResult::getInputString).toList().toString();
            }

            @Override
            public String getPrettyString() {
                return loggablesList.stream().map(ILoggableResult::getPrettyString).collect(Collectors.joining(", "));
            }
        };
    }
}