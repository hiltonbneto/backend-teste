CREATE TABLE player (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE league (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    number_player INT NOT NULL,
    format VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE league_player (
    league_id UUID NOT NULL,
    player_id UUID NOT NULL,
    PRIMARY KEY (league_id, player_id),
    FOREIGN KEY (league_id) REFERENCES league(id) ON DELETE CASCADE,
    FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE
);
