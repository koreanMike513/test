package com.f_lab.joyeuse_planete.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = -1550470550L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFood food = new QFood("food");

    public final com.f_lab.joyeuse_planete.core.domain.base.QBaseEntity _super = new com.f_lab.joyeuse_planete.core.domain.base.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> collectionEndTime = createDateTime("collectionEndTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> collectionStartTime = createDateTime("collectionStartTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QCurrency currency;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath foodName = createString("foodName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final QStore store;

    public final StringPath tags = createString("tags");

    public final NumberPath<Integer> totalQuantity = createNumber("totalQuantity", Integer.class);

    public QFood(String variable) {
        this(Food.class, forVariable(variable), INITS);
    }

    public QFood(Path<? extends Food> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFood(PathMetadata metadata, PathInits inits) {
        this(Food.class, metadata, inits);
    }

    public QFood(Class<? extends Food> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.currency = inits.isInitialized("currency") ? new QCurrency(forProperty("currency")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

