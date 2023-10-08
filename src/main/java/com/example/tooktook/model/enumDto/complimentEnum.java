package com.example.tooktook.model.enumDto;

public enum complimentEnum {

    QUESTION_1("올해 %s에게 해줄 칭찬을 말해줘!"),
    QUESTION_2("당신이 지켜주고 싶은 %s만의 올해 매력은 뭐야?"),
    QUESTION_3("%s한테 꼭 해주고 싶었던 말이 뭐야?")
    ;
    private final String text;

    complimentEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


}
