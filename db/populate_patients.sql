INSERT INTO `Roles` (`Description`) VALUES ('patient');

INSERT INTO `person` (`NameLast`, `NameFirst`, `Phone`, `username`, `password`
	, `street`, `City`, `Province`, `PostalCode`, `RoleID`) VALUES
	('Tables', 'Bobby', '519123456', 'btables', 'password', '123 Street Ave', 'Waterloo', 'ON', 'N2L3V9', 1),
	('Acula', 'Steve', '5199874754', 'sacula', 'vampire', '345 Blah Blvd', 'Waterloo', 'ON', 'N2L2T8', 2)

INSERT INTO `doctor` (`PersonID`) VALUES (
