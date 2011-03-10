package org.bimserver.database.actions;

import org.bimserver.database.BimDatabaseException;
import org.bimserver.database.BimDatabaseSession;
import org.bimserver.database.BimDeadlockException;
import org.bimserver.database.log.AccessMethod;
import org.bimserver.database.query.conditions.AttributeCondition;
import org.bimserver.database.query.conditions.Condition;
import org.bimserver.database.query.literals.StringLiteral;
import org.bimserver.database.store.StorePackage;
import org.bimserver.database.store.User;
import org.bimserver.shared.UserException;

public class GetUserByNameDatabaseAction extends BimDatabaseAction<User> {

	private final String username;

	public GetUserByNameDatabaseAction(BimDatabaseSession bimDatabaseSession, AccessMethod accessMethod, String username) {
		super(bimDatabaseSession, accessMethod);
		this.username = username;
	}

	@Override
	public User execute() throws UserException, BimDeadlockException, BimDatabaseException {
		Condition condition = new AttributeCondition(StorePackage.eINSTANCE.getUser_Username(), new StringLiteral(username));
		return getDatabaseSession().querySingle(condition, User.class, false);
	}
}