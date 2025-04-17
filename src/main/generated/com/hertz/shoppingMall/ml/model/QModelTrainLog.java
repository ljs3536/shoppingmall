package com.hertz.shoppingMall.ml.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QModelTrainLog is a Querydsl query type for ModelTrainLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QModelTrainLog extends EntityPathBase<ModelTrainLog> {

    private static final long serialVersionUID = -1246570557L;

    public static final QModelTrainLog modelTrainLog = new QModelTrainLog("modelTrainLog");

    public final DateTimePath<java.time.LocalDateTime> executedAt = createDateTime("executedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final StringPath modelName = createString("modelName");

    public final BooleanPath success = createBoolean("success");

    public final EnumPath<ModelType> type = createEnum("type", ModelType.class);

    public QModelTrainLog(String variable) {
        super(ModelTrainLog.class, forVariable(variable));
    }

    public QModelTrainLog(Path<? extends ModelTrainLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModelTrainLog(PathMetadata metadata) {
        super(ModelTrainLog.class, metadata);
    }

}

