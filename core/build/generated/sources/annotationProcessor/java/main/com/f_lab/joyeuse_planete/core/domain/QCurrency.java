package com.f_lab.joyeuse_planete.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCurrency is a Querydsl query type for Currency
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCurrency extends EntityPathBase<Currency> {

    private static final long serialVersionUID = -1357402083L;

    public static final QCurrency currency = new QCurrency("currency");

    public final com.f_lab.joyeuse_planete.core.domain.base.QBaseTimeEntity _super = new com.f_lab.joyeuse_planete.core.domain.base.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath currencyCode = createString("currencyCode");

    public final StringPath currencySymbol = createString("currencySymbol");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<java.math.RoundingMode> roundingMode = createEnum("roundingMode", java.math.RoundingMode.class);

    public final NumberPath<Integer> roundingScale = createNumber("roundingScale", Integer.class);

    public QCurrency(String variable) {
        super(Currency.class, forVariable(variable));
    }

    public QCurrency(Path<? extends Currency> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCurrency(PathMetadata metadata) {
        super(Currency.class, metadata);
    }

}

