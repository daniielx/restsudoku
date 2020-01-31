package com.example.restsudoku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SudokuController {

    private BoardService boardService;
    private BoardServiceX9 boardServiceX9;

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setBoardServiceX9(BoardServiceX9 boardServiceX9) {
        this.boardServiceX9 = boardServiceX9;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/{userName}/{userId}")
    public String saveGame(@PathVariable String userName,
                           @PathVariable int userId, @RequestBody String game) {
        return boardService.saveUserGame(userName, userId, game);

    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{userName}/{userId}")
    public String openGame(@PathVariable String userName, @PathVariable int userId) {
        return boardService.getSavedGame(userName,userId);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/clue")
    public String giveClue(@RequestBody String str) {
        return boardService.getClue(str);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/check")
    public String checkRules(@RequestBody String str) {
        return boardService.checkBoard(str);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/board")
    public String checkBoard(@RequestBody String str) {
        return boardService.validateBoard(str);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/board/9x9")
    public String checkBoardX9(@RequestBody String str) {
        return boardServiceX9.validateBoard(str);
    }

}
