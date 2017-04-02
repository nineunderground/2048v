/*
 * 
 */
package org.nineunderground.game.ui;

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
    private Button up;
    private Button down;
    private Button left;
    private Button right;
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
	header.setHeight(100, Unit.PERCENTAGE);
	Button newGame = new Button(FontAwesome.GAMEPAD);
	newGame.addClickListener(c -> startGame());
	header.addComponent(newGame);
	Label scoreLabel = new Label();
	game.setResetedScore(scoreLabel);
	header.addComponent(scoreLabel);
	header.setExpandRatio(newGame, 3);
	header.setExpandRatio(scoreLabel, 7);
	header.setComponentAlignment(newGame, Alignment.MIDDLE_LEFT);
	header.setComponentAlignment(scoreLabel, Alignment.MIDDLE_RIGHT);
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
	footer.setWidth(100, Unit.PERCENTAGE);
	footer.setHeight(100, Unit.PERCENTAGE);
	up = new Button();
	up.setIcon(FontAwesome.ARROW_UP);
	up.addClickListener(c -> {
	    game.doSwipe(MODE.TO_TOP);
	    if (game.isGameFinished())
		enableButtons(false);
	});
	footer.addComponent(up);
	down = new Button();
	down.setIcon(FontAwesome.ARROW_DOWN);
	down.addClickListener(c -> {
	    game.doSwipe(MODE.TO_BOTTOM);
	    if (game.isGameFinished())
		enableButtons(false);
	});
	footer.addComponent(down);
	left = new Button();
	left.setIcon(FontAwesome.ARROW_LEFT);
	left.addClickListener(c -> {
	    game.doSwipe(MODE.TO_LEFT);
	    if (game.isGameFinished())
		enableButtons(false);
	});
	footer.addComponent(left);
	right = new Button();
	right.setIcon(FontAwesome.ARROW_RIGHT);
	right.addClickListener(c -> {
	    game.doSwipe(MODE.TO_RIGHT);
	    if (game.isGameFinished())
		enableButtons(false);
	});
	footer.addComponent(right);
	addComponent(footer);
	enableButtons(false);
	return footer;
    }

    /**
     * Enable buttons.
     *
     * @param hasToEnable
     *            the has to enable
     */
    public void enableButtons(boolean hasToEnable) {
	up.setEnabled(hasToEnable);
	down.setEnabled(hasToEnable);
	right.setEnabled(hasToEnable);
	left.setEnabled(hasToEnable);
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
