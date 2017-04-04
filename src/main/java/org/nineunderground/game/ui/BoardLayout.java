/*
 * 
 */
package org.nineunderground.game.ui;

import java.util.ArrayList;
import java.util.List;

import org.nineunderground.game.GameEngine;
import org.nineunderground.game.GameEngine.MODE;
import org.nineunderground.game.table.BoardRow;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * Layout class to display all components needed for game play
 * 
 * @author inaki
 *
 */
public class BoardLayout extends VerticalLayout {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 107355173261153181L;
    private static final String FIRST_COLUMN = "col1.number";
    private static final String SECOND_COLUMN = "col2.number";
    private static final String THIRD_COLUMN = "col3.number";
    private static final String FOURTH_COLUMN = "col4.number";

    private BeanItemContainer<BoardRow> container;
    private List<Button> btnList;
    private GameEngine game;

    /**
     * Instantiates a new board layout.
     * 
     * @param game
     */
    public BoardLayout(GameEngine game) {
	this.game = game;
	setLayout();
    }

    /**
     * Sets the layout.
     * 
     * @param game
     */
    private void setLayout() {
	removeAllComponents();
	setSizeFull();
	setSpacing(false);
	HorizontalLayout header = createHeaderLayout();
	addComponent(header);
	HorizontalLayout tableLayout = createTableLayout();
	addComponent(tableLayout);
	HorizontalLayout footer = createCommandLayout();
	// Setting layout
	setExpandRatio(header, 1);
	setExpandRatio(tableLayout, 7);
	setExpandRatio(footer, 2);
    }

    /**
     * Creates the header layout.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout createHeaderLayout() {
	HorizontalLayout header = new HorizontalLayout();
	header.setWidth(100, Unit.PERCENTAGE);
	header.addStyleName("header-layout");
	Button newGame = new Button("NEW GAME");
	newGame.addClickListener(c -> startGame());
	newGame.addStyleName("new-game-button");
	header.addComponent(newGame);
	Label scoreLabel = new Label();
	scoreLabel.addStyleName("score-counter");
	scoreLabel.setWidth(100, Unit.PERCENTAGE);
	game.setResetedScore(scoreLabel);
	header.addComponent(scoreLabel);
	header.setComponentAlignment(newGame, Alignment.MIDDLE_LEFT);
	header.setComponentAlignment(scoreLabel, Alignment.BOTTOM_RIGHT);
	return header;
    }

    /**
     * Creates the table layout.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout createTableLayout() {
	HorizontalLayout tableLayout = new HorizontalLayout();
	tableLayout.setSizeFull();
	Table gameBoard = createBoardTable();
	gameBoard.setSizeFull();
	tableLayout.addComponent(gameBoard);
	return tableLayout;
    }

    /**
     * Creates the command layout.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout createCommandLayout() {
	HorizontalLayout footer = new HorizontalLayout();
	footer.addStyleName("footer-layout");
	footer.setWidth(100, Unit.PERCENTAGE);
	footer.setHeight(100, Unit.PERCENTAGE);

	btnList = new ArrayList<>();
	Button up = createButton(FontAwesome.ARROW_UP, MODE.TO_TOP);
	Button down = createButton(FontAwesome.ARROW_DOWN, MODE.TO_BOTTOM);
	Button left = createButton(FontAwesome.ARROW_LEFT, MODE.TO_LEFT);
	Button right = createButton(FontAwesome.ARROW_RIGHT, MODE.TO_RIGHT);

	VerticalLayout centralButtons = new VerticalLayout();
	centralButtons.addComponent(up);
	centralButtons.addComponent(down);
	centralButtons.setComponentAlignment(up, Alignment.MIDDLE_CENTER);
	centralButtons.setComponentAlignment(down, Alignment.MIDDLE_CENTER);
	centralButtons.setSpacing(true);

	footer.addComponent(left);
	footer.addComponent(centralButtons);
	footer.addComponent(right);

	footer.setComponentAlignment(left, Alignment.MIDDLE_CENTER);
	footer.setComponentAlignment(right, Alignment.MIDDLE_CENTER);

	addComponent(footer);
	enableButtons(false);
	return footer;
    }

    /**
     * Creates the button.
     *
     * @param fontIcon
     *            the font icon
     * @param modeToSwipe
     *            the mode to swipe
     */
    private Button createButton(FontAwesome fontIcon, MODE modeToSwipe) {
	Button btn = new Button();
	btn.setIcon(fontIcon);
	btn.addClickListener(c -> {
	    game.doSwipe(modeToSwipe);
	    if (game.isGameFinished())
		enableButtons(false);
	});
	btn.setWidth(100, Unit.PIXELS);
	btn.setHeight(50, Unit.PIXELS);
	btn.addStyleName("swipe-button");
	btnList.add(btn);
	return btn;
    }

    /**
     * Enable buttons.
     *
     * @param hasToEnable
     *            the has to enable
     */
    public void enableButtons(boolean hasToEnable) {
	for (Button btn : btnList) {
	    btn.setEnabled(hasToEnable);
	}
    }

    /**
     * Start game.
     * 
     * @param game
     */
    public void startGame() {
	game.initGame(container);
	enableButtons(true);
    }

    /**
     * Creates the board table.
     *
     * @return the table
     */
    private Table createBoardTable() {
	container = new BeanItemContainer<>(BoardRow.class);
	container.removeAllItems();
	container.addNestedContainerProperty(FIRST_COLUMN);
	container.addNestedContainerProperty(SECOND_COLUMN);
	container.addNestedContainerProperty(THIRD_COLUMN);
	container.addNestedContainerProperty(FOURTH_COLUMN);
	Table board = new Table("", container);
	board.addStyleName("score-table");
	board.setVisibleColumns(new Object[] { FIRST_COLUMN, SECOND_COLUMN, THIRD_COLUMN, FOURTH_COLUMN });
	board.setPageLength(0);
	board.setColumnExpandRatio(FIRST_COLUMN, 1);
	board.setColumnExpandRatio(SECOND_COLUMN, 1);
	board.setColumnExpandRatio(THIRD_COLUMN, 1);
	board.setColumnExpandRatio(FOURTH_COLUMN, 1);
	return board;
    }

}
