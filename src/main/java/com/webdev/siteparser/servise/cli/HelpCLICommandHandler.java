package com.webdev.siteparser.servise.cli;

import org.springframework.stereotype.Service;

@Service("help")
public class HelpCLICommandHandler implements CLICommandHandler{
    @Override
    public void handleCommand(String command, HtmlAnaliseCLI cli) {
        System.out.println("help information");
    }
}
