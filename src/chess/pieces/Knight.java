package chess.pieces;

import java.util.Arrays;
import java.util.List;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		// right up
		Position ru = new Position(position.getRow() - 1, position.getColumn() + 2);

		// right down
		Position rd = new Position(position.getRow() + 1, position.getColumn() + 2);

		// left up
		Position lu = new Position(position.getRow() - 1, position.getColumn() - 2);

		// left down
		Position ld = new Position(position.getRow() - 1, position.getColumn() - 2);

		// up right
		Position ur = new Position(position.getRow() - 2, position.getColumn() + 1);

		// up left
		Position ul = new Position(position.getRow() - 2, position.getColumn() - 1);
		
		//down right
		Position dr = new Position(position.getRow() + 2, position.getColumn() + 1);
		
		//down left
		Position dl = new Position(position.getRow() + 2, position.getColumn() - 1);
		
		List<Position> validPositions = Arrays.asList(ru, rd, lu, ld, ur, ul, dr, dl);
		
		validPositions.forEach(p -> {
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			else if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		});
		
		return mat;
	}

	@Override
	public String toString() {
		return "N";
	}
}
