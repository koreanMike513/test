package com.f_lab.joyeuse_planete.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVoucher is a Querydsl query type for Voucher
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoucher extends EntityPathBase<Voucher> {

    private static final long serialVersionUID = -669082334L;

    public static final QVoucher voucher = new QVoucher("voucher");

    public final com.f_lab.joyeuse_planete.core.domain.base.QBaseTimeEntity _super = new com.f_lab.joyeuse_planete.core.domain.base.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<java.math.BigDecimal> discountRate = createNumber("discountRate", java.math.BigDecimal.class);

    public final DateTimePath<java.time.LocalDateTime> expiryDate = createDateTime("expiryDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public QVoucher(String variable) {
        super(Voucher.class, forVariable(variable));
    }

    public QVoucher(Path<? extends Voucher> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVoucher(PathMetadata metadata) {
        super(Voucher.class, metadata);
    }

}

