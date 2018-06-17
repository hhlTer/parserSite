package com.webdev.siteparser.servise.cli;

import org.springframework.stereotype.Service;

@Service("exitCLI")
public class ExitCLICommandHandler implements CLICommandHandler {
    @Override
    public void handleCommand(String command, HtmlAnaliseCLI cli) {
        System.out.println("Bye");
        System.exit(0);
    }
}
