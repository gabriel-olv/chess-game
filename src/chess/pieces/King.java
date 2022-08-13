package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	public ChessMatch getChessMatch() {
		return chessMatch;
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor().equals(getColor()) && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// upper right diagonal
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// lower right diagonal
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// lower left diagonal
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// upper left diagonal
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// #specialmove castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			Position aux = new Position(position.getRow(), position.getColumn());
			boolean[] canMove = new boolean[5];
			
			for (int i = 0; i < canMove.length; i++) {
				if (i < 2) {
					aux.setColumn(position.getColumn() + i + 1);
				}
				else {
					aux.setColumn(position.getColumn() + 1 - i);
				}
				canMove[i] = canMove(aux);
			}
			
			//right castling
			aux.setValues(position.getRow(), position.getColumn() + 3);
			if (canMove[0] && canMove[1] && testRookCastling(aux)) {
				mat[position.getRow()][position.getColumn() + 2] = true;
			}
			
			//left castling
			aux.setValues(position.getRow(), position.getColumn() - 4);
			if (canMove[2] && canMove[3] && canMove[4] && testRookCastling(aux)) {
				mat[position.getRow()][position.getColumn() - 2] = true;
			}
		}
		return mat;
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || !p.getColor().equals(getColor());
	}

	@Override
	public String toString() {
		return "K";
	}
}
