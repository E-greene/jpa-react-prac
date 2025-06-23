package com.example.jpaprac.presentation.dto.board;

public class UpdateBoardCommand {

    private final String title;
    private final String content;

    public UpdateBoardCommand(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static UpdateBoardCommand fromUpdateBoardRequest(UpdateBoardRequest updateBoardRequest) {
        return new UpdateBoardCommand(
                updateBoardRequest.getTitle(),
                updateBoardRequest.getContent()
        );
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
