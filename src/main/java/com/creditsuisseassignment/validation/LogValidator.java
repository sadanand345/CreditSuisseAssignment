package com.creditsuisseassignment.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
public class LogValidator {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(LogValidator.class);

    public boolean validateInput(String []args) throws URISyntaxException {
        boolean isValidCommandLineArgument = validateCommandLineArguments(args);
        LOGGER.info("isValidCommandLineArgument: {}", isValidCommandLineArgument);
        boolean isValidFilePath = validateFilePath(args);
        LOGGER.info("isValidFilePath: {}", isValidFilePath);
        return isValidCommandLineArgument && isValidFilePath;
    }

    public boolean validateCommandLineArguments(String[] args) {
        LOGGER.info("Check the command line arguments");

        //Check if empty
        if(args==null) {
            LOGGER.error("Null or empty arguments. Please add 1 argument!", args);
            return false;
        }

        //Check if argument size is > 1
        if(args.length > 1) {
            LOGGER.error("Please enter only 1 argument", Arrays.toString(args));
            return false;
        }

        LOGGER.info("Command line arguments are not empty and equal to 1");

        return true;
    }

    public boolean validateFilePath(String[] args) throws URISyntaxException {
        //Check if the user has rights, if not isReadable will throw a SecurityException
        //Check if the path exists, or else throw InvalidPathException
        LOGGER.info("Trying to read the file at Path: {}", args[0]);
        return Files.isReadable(Paths.get(ClassLoader.getSystemResource(args[0]).toURI()));
    }

}
