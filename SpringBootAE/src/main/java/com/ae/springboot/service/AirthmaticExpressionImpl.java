package com.ae.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.springboot.RecordNotFoundException;
import com.ae.springboot.model.AeEntity;
import com.ae.springboot.repository.AeRepository;

/**
 * 
 * @author Kamlesh.Singh
 *
 */
@Service
public class AirthmaticExpressionImpl implements AirthmaticExpression {

	@Autowired
	AeRepository repository;

	/** * Calculating Arithmetic Expression */

	public int eval(final String str) {
		return new Object() {
			int pos = -1, ch;

			void nextChar() {
				ch = (++pos < str.length()) ? str.charAt(pos) : -1;
			}

			boolean eat(int charToEat) {
				while (ch == ' ')
					nextChar();
				if (ch == charToEat) {
					nextChar();
					return true;
				}
				return false;
			}

			int parse() {
				nextChar();
				int x = parseExpression();
				if (pos < str.length())
					throw new RuntimeException("Unexpected: " + (char) ch);
				return x;
			}

			// Grammar:
			// expression = term | expression `+` term | expression `-` term
			// term = factor | term `*` factor | term `/` factor
			// factor = `+` factor | `-` factor | `(` expression `)`
			// | number | functionName factor | factor `^` factor

			int parseExpression() {
				int x = parseTerm();
				for (;;) {
					if (eat('+'))
						x += parseTerm(); // addition
					else if (eat('-'))
						x -= parseTerm(); // subtraction
					else
						return x;
				}
			}

			int parseTerm() {
				int x = parseFactor();
				for (;;) {
					if (eat('*'))
						x *= parseFactor(); // multiplication
					else if (eat('/'))
						x /= parseFactor(); // division
					else
						return x;
				}
			}

			int parseFactor() {
				if (eat('+'))
					return parseFactor(); // unary plus
				if (eat('-'))
					return -parseFactor(); // unary minus

				int x;
				int startPos = this.pos;
				if (eat('(')) { // parentheses
					x = parseExpression();
					eat(')');
				} else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
					while ((ch >= '0' && ch <= '9') || ch == '.')
						nextChar();
					x = Integer.parseInt(str.substring(startPos, this.pos));
				} else if (ch >= 'a' && ch <= 'z') { // functions
					while (ch >= 'a' && ch <= 'z')
						nextChar();
					String func = str.substring(startPos, this.pos);
					x = parseFactor();
					if (func.equals("sqrt"))
						x = (int) Math.sqrt(x);
					else if (func.equals("sin"))
						x = (int) Math.sin(Math.toRadians(x));
					else if (func.equals("cos"))
						x = (int) Math.cos(Math.toRadians(x));
					else if (func.equals("tan"))
						x = (int) Math.tan(Math.toRadians(x));
					else
						throw new RuntimeException("Unknown function: " + func);
				} else {
					throw new RuntimeException("Unexpected: " + (char) ch);
				}

				if (eat('^'))
					x = (int) Math.pow(x, parseFactor()); // exponentiation

				return x;
			}
		}.parse();
	}

	@Override
	public List<AeEntity> getAllAirthmeticExp() throws RecordNotFoundException {

		List<AeEntity> aeList = repository.findAll();
		if (aeList.size() > 0) {
			return aeList;
		} else {
			return new ArrayList<AeEntity>();
		}
	}

	@Override
	public AeEntity createOrUpdateAirthmeticExp(AeEntity entity) throws RecordNotFoundException {

		Optional<AeEntity> ae = repository.findById(entity.getId());
		if (ae.isPresent()) {
			AeEntity newEntity = ae.get();
			newEntity.setInput(entity.getInput());
			newEntity.setOutput(entity.getOutput());
			newEntity = repository.save(newEntity);
			return newEntity;
		} else {
			entity = repository.save(entity);
			return entity;
		}
	}

	@Override
	public List<AeEntity> getAllAEOutputStartWithOne() throws RecordNotFoundException {
		List<AeEntity> entityList = getAllAirthmeticExp();
		List<AeEntity> entityListNew = entityList.stream().filter(ae -> ae.getOutput().startsWith("1"))
				.collect(Collectors.toList());
		return entityListNew;
	}

}
