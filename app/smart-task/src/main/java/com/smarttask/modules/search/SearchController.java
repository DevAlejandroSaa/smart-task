package com.smarttask.modules.search;

import java.util.List;

import com.smarttask.console.screen.ConsoleScreen;
import com.smarttask.core.models.Task;

public class SearchController {

    private static final SearchController INSTANCE = new SearchController();

    private final SearchService searchService;
    private final ConsoleScreen consoleScreen;

    private SearchController() {
        this.searchService = SearchServiceImpl.getInstance();
        this.consoleScreen = ConsoleScreen.getInstance();
    }

    public static SearchController getInstance() {
        return INSTANCE;
    }

    public List<Task> listTask() {
        this.consoleScreen.show("Listar datos", "", "");
        return this.searchService.listTask();
    }

}
